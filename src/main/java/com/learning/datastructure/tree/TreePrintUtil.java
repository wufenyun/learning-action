package com.learning.datastructure.tree;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TreePrintUtil {

    public static void pirnt(BinaryTreeNode root) {
        // 找到左边的最大偏移量
        int maxLeftOffset = findMaxOffset(root, 0, true);
        int maxRightOffset = findMaxOffset(root, 0, false);
        int offset = Math.max(maxLeftOffset, maxRightOffset);
        // 计算最大偏移量
        Map<Integer, PrintLine> lineMap = new HashMap();
        calculateLines(root, offset, lineMap, 0, true);
        Iterator<Integer> lineNumbers = lineMap.keySet().iterator();
        int maxLine = 0;
        while (lineNumbers.hasNext()) {
            int lineNumber = lineNumbers.next();
            if (lineNumber > maxLine) {
                maxLine = lineNumber;
            }
        }
        for (int i = 0; i <= maxLine; i++) {
            PrintLine line = lineMap.get(i);
            if (line != null) {
                System.out.println(line.getLineString());
            }
        }

    }

    private static void calculateLines(BinaryTreeNode parent, int offset, Map<Integer, PrintLine> lineMap, int level,
                                       boolean right) {
        if (parent == null) {
            return;
        }
        int nameoffset = parent.toString().length() / 2;
        PrintLine line = lineMap.get(level);
        if (line == null) {
            line = new PrintLine();
            lineMap.put(level, line);
        }
        line.putString(right ? offset : (offset - nameoffset), parent.toString());
        // 判断有没有下一级
        if (parent.getLeft() == null && parent.getRight() == null) {
            return;
        }
        // 如果有，添加分割线即/\
        PrintLine separateLine = lineMap.get(level + 1);
        if (separateLine == null) {
            separateLine = new PrintLine();
            lineMap.put(level + 1, separateLine);
        }
        if (parent.getLeft() != null) {
            separateLine.putString(offset - 1, "/");
            calculateLines(parent.getLeft(), offset - nameoffset - 1, lineMap, level + 2, false);
        }
        if (parent.getRight() != null) {
            separateLine.putString(offset + nameoffset + 1, "\\");
            calculateLines(parent.getRight(), offset + nameoffset + 1, lineMap, level + 2, true);
        }

    }

    /**
     * 需要打印的某一行
     *
     * @author zhuguohui
     *
     */
    private static class PrintLine {
        /**
         * 记录了offset和String的map
         */
        Map<Integer, String> printItemsMap = new HashMap<>();
        int maxOffset = 0;

        public void putString(int offset, String info) {
            printItemsMap.put(offset, info);
            if (offset > maxOffset) {
                maxOffset = offset;
            }
        }

        public String getLineString() {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i <= maxOffset; i++) {
                String info = printItemsMap.get(i);
                if (info == null) {
                    buffer.append(" ");
                } else {
                    buffer.append(info);
                    i += info.length();
                }
            }
            return buffer.toString();
        }

    }

    private static int findMaxOffset(BinaryTreeNode parent, int offset, boolean findLeft) {
        if (parent != null) {
            offset += parent.toString().length();
        }
        if (findLeft && parent.getLeft() != null) {
            offset += 1;
            return findMaxOffset(parent.getLeft(), offset, findLeft);
        }
        if (!findLeft && parent.getRight() != null) {
            return findMaxOffset(parent.getRight(), offset, findLeft);
        }
        return offset;
    }


    @Test
    public void test() {
        BinaryTreeNode root = new BinaryTreeNode(4);
        BinaryTreeNode left = new BinaryTreeNode(1,new BinaryTreeNode(2),new BinaryTreeNode(3));
        BinaryTreeNode right = new BinaryTreeNode(5,new BinaryTreeNode(6),new BinaryTreeNode(7));
        root.setLeft(left);
        root.setRight(right);
        TreePrintUtil.pirnt(root);
    }
}
