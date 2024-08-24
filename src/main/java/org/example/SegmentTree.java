package org.example;

import java.util.Arrays;

public class SegmentTree {
  private static final int DEFAULT_VALUE = 0;
  // private static final int DEFAULT_VALUE = Integer.MAX_VALUE; // for min
  // private static final int DEFAULT_VALUE = Integer.MIN_VALUE; // for max

  private int N;              // size
  private int[] tree;         // segment tree

  // merge operation
  private int merge(int left, int right) {
    return left + right;        // sum
    // return Math.min(left, right);  // min
    // return Math.max(left, right);  // max
    // ...
  }

  public void build(int[] arr) {
    N = arr.length;
    tree = new int[N * 4];
    buildRec(arr, 1, 0, N - 1);
  }

  // inclusive
  public int update(int index, int newValue) {
    return updateRec(index, newValue, 1, 0, N - 1);
  }

  // inclusive
  public int update(int left, int right, int newValue) {
    return updateRec(left, right, newValue, 1, 0, N - 1);
  }

  // inclusive
  public int query(int left, int right) {
    return queryRec(left, right, 1, 0, N - 1);
  }

  private int buildRec(int[] arr, int node, int nodeLeft, int nodeRight) {
    if (nodeLeft == nodeRight)
      return tree[node] = arr[nodeLeft];

    int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
    int leftVal = buildRec(arr, node * 2, nodeLeft, mid);
    int rightVal = buildRec(arr, node * 2 + 1, mid + 1, nodeRight);
    return tree[node] = merge(leftVal, rightVal);
  }

  // index 하나에 적용된다.
  private int updateRec(int index, int newValue, int node, int nodeLeft, int nodeRight) {
    if (index < nodeLeft || nodeRight < index)
      return tree[node];

    if (nodeLeft == nodeRight)
      return tree[node] = newValue;

    int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
    int leftVal = updateRec(index, newValue, node * 2, nodeLeft, mid);
    int rightVal = updateRec(index, newValue, node * 2 + 1, mid + 1, nodeRight);
    return tree[node] = merge(leftVal, rightVal);
  }

  // 구간에 대해서 적용
  // laze praperation 적용
  private int updateRec(int left, int right, int newValue, int node, int nodeLeft, int nodeRight) {
    if (right < nodeLeft || nodeRight < left)
      return tree[node];

    if (nodeLeft == nodeRight)
      return tree[node] = newValue;

    int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
    int leftVal = updateRec(left, right, newValue, node * 2, nodeLeft, mid);
    int rightVal = updateRec(left, right, newValue, node * 2 + 1, mid + 1, nodeRight);
    return tree[node] = merge(leftVal, rightVal);
  }

  private int queryRec(int left, int right, int node, int nodeLeft, int nodeRight) {
    if (right < nodeLeft || nodeRight < left)
      return DEFAULT_VALUE;   // default value

    if (left <= nodeLeft && nodeRight <= right)
      return tree[node];

    int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
    return merge(queryRec(left, right, node * 2, nodeLeft, mid),
      queryRec(left, right, node * 2 + 1, mid + 1, nodeRight));
  }

  public static void main(String[] args) {
    SegmentTree segTree = new SegmentTree();
    int[] arr = {1, 3, 5, 7, 9, 11};
    segTree.build(arr);

    System.out.println(segTree.query(1, 3)); // Output: 15
    segTree.update(1, 3, 10);
    System.out.println(segTree.query(1, 3)); // Output: 24
  }
}
