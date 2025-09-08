package com.daqem.uilib.skilltree;

import com.daqem.uilib.api.skilltree.ISkillTreeItem;
import com.google.common.collect.Lists;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SkillTreePositioner {

    private final ISkillTreeItem node;
    @Nullable
    private final SkillTreePositioner parent;
    @Nullable
    private final SkillTreePositioner previousSibling;
    private final int childIndex;
    private final List<SkillTreePositioner> children = Lists.newArrayList();
    private SkillTreePositioner ancestor;
    @Nullable
    private SkillTreePositioner thread;
    private int x;
    private float y;
    private float mod;
    private float change;
    private float shift;

    public SkillTreePositioner(ISkillTreeItem item, @Nullable SkillTreePositioner parent, @Nullable SkillTreePositioner previousSibling, int childIndex, int x) {
        this.node = item;
        this.parent = parent;
        this.previousSibling = previousSibling;
        this.childIndex = childIndex;
        this.ancestor = this;
        this.x = x;
        this.y = -1.0F;
        SkillTreePositioner SkillTreePositioner = null;

        for (ISkillTreeItem skillTreeItem : item.getChildren()) {
            SkillTreePositioner = this.addChild(skillTreeItem, SkillTreePositioner);
        }
    }

    private SkillTreePositioner addChild(ISkillTreeItem child, @Nullable SkillTreePositioner previousSibling) {
//        if (child.advancement().display().isPresent()) {
            previousSibling = new SkillTreePositioner(child, this, previousSibling, this.children.size() + 1, this.x + 1);
            this.children.add(previousSibling);
//        } else {
//            for (ISkillTreeItem advancementNode : child.children()) {
//                previousSibling = this.addChild(advancementNode, previousSibling);
//            }
//        }

        return previousSibling;
    }

    private void firstWalk() {
        if (this.children.isEmpty()) {
            if (this.previousSibling != null) {
                this.y = this.previousSibling.y + 1.0F;
            } else {
                this.y = 0.0F;
            }

        } else {
            SkillTreePositioner SkillTreePositioner = null;

            for (SkillTreePositioner SkillTreePositioner2 : this.children) {
                SkillTreePositioner2.firstWalk();
                SkillTreePositioner = SkillTreePositioner2.apportion(SkillTreePositioner == null ? SkillTreePositioner2 : SkillTreePositioner);
            }

            this.executeShifts();
            float f = (this.children.getFirst().y + this.children.getLast().y) / 2.0F;
            if (this.previousSibling != null) {
                this.y = this.previousSibling.y + 1.0F;
                this.mod = this.y - f;
            } else {
                this.y = f;
            }

        }
    }

    private float secondWalk(float offsetY, int columnX, float subtreeTopY) {
        this.y += offsetY;
        this.x = columnX;
        if (this.y < subtreeTopY) {
            subtreeTopY = this.y;
        }

        for (SkillTreePositioner SkillTreePositioner : this.children) {
            subtreeTopY = SkillTreePositioner.secondWalk(offsetY + this.mod, columnX + 1, subtreeTopY);
        }

        return subtreeTopY;
    }

    private void thirdWalk(float y) {
        this.y += y;

        for (SkillTreePositioner SkillTreePositioner : this.children) {
            SkillTreePositioner.thirdWalk(y);
        }

    }

    private void executeShifts() {
        float f = 0.0F;
        float g = 0.0F;

        for (int i = this.children.size() - 1; i >= 0; --i) {
            SkillTreePositioner SkillTreePositioner = this.children.get(i);
            SkillTreePositioner.y += f;
            SkillTreePositioner.mod += f;
            g += SkillTreePositioner.change;
            f += SkillTreePositioner.shift + g;
        }

    }

    @Nullable
    private SkillTreePositioner previousOrThread() {
        if (this.thread != null) {
            return this.thread;
        } else {
            return !this.children.isEmpty() ? this.children.getFirst() : null;
        }
    }

    @Nullable
    private SkillTreePositioner nextOrThread() {
        if (this.thread != null) {
            return this.thread;
        } else {
            return !this.children.isEmpty() ? this.children.getLast() : null;
        }
    }

    private SkillTreePositioner apportion(SkillTreePositioner node) {
        if (this.previousSibling == null) {
            return node;
        } else {
            SkillTreePositioner skillTreePositioner = this;
            SkillTreePositioner skillTreePositioner2 = this;
            SkillTreePositioner skillTreePositioner3 = this.previousSibling;
            SkillTreePositioner skillTreePositioner4 = this.parent.children.getFirst();
            float f = this.mod;
            float g = this.mod;
            float h = skillTreePositioner3.mod;

            float i;
            for (i = skillTreePositioner4.mod; skillTreePositioner3.nextOrThread() != null && skillTreePositioner.previousOrThread() != null; g += skillTreePositioner2.mod) {
                skillTreePositioner3 = skillTreePositioner3.nextOrThread();
                skillTreePositioner = skillTreePositioner.previousOrThread();
                skillTreePositioner4 = skillTreePositioner4.previousOrThread();
                skillTreePositioner2 = skillTreePositioner2.nextOrThread();
                skillTreePositioner2.ancestor = this;
                float j = skillTreePositioner3.y + h - (skillTreePositioner.y + f) + 1.0F;
                if (j > 0.0F) {
                    skillTreePositioner3.getAncestor(this, node).moveSubtree(this, j);
                    f += j;
                    g += j;
                }

                h += skillTreePositioner3.mod;
                f += skillTreePositioner.mod;
                i += skillTreePositioner4.mod;
            }

            if (skillTreePositioner3.nextOrThread() != null && skillTreePositioner2.nextOrThread() == null) {
                skillTreePositioner2.thread = skillTreePositioner3.nextOrThread();
                skillTreePositioner2.mod += h - g;
            } else {
                if (skillTreePositioner.previousOrThread() != null && skillTreePositioner4.previousOrThread() == null) {
                    skillTreePositioner4.thread = skillTreePositioner.previousOrThread();
                    skillTreePositioner4.mod += f - i;
                }

                node = this;
            }

            return node;
        }
    }

    private void moveSubtree(SkillTreePositioner node, float shift) {
        float f = (float) (node.childIndex - this.childIndex);
        if (f != 0.0F) {
            node.change -= shift / f;
            this.change += shift / f;
        }

        node.shift += shift;
        node.y += shift;
        node.mod += shift;
    }

    private SkillTreePositioner getAncestor(SkillTreePositioner self, SkillTreePositioner other) {
        return this.ancestor != null && self.parent.children.contains(this.ancestor) ? this.ancestor : other;
    }

    private void finalizePosition() {
        this.node.setLocation((float) this.x, this.y);
        if (!this.children.isEmpty()) {
            for (SkillTreePositioner skillTreePositioner : this.children) {
                skillTreePositioner.finalizePosition();
            }
        }

    }

    public static void run(ISkillTreeItem rootItem) {
        SkillTreePositioner skillTreePositioner = new SkillTreePositioner(rootItem, null, null, 1, 0);
        skillTreePositioner.firstWalk();
        float f = skillTreePositioner.secondWalk(0.0F, 0, skillTreePositioner.y);
        if (f < 0.0F) {
            skillTreePositioner.thirdWalk(-f);
        }

        skillTreePositioner.finalizePosition();
    }
}
