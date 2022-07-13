package com.xiaohua.leetcode;

/**
 * @auther XIAOHUA
 * @date 2022/6/27 22:11
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
public class Pro206 {
    public ListNode reverseList(ListNode head) {
        ListNode pre=null,cur=head;
        while (cur!=null){
            ListNode temp=cur.next;
            cur.next=pre;
            pre=cur;
            cur=temp;
        }
        return pre;

    }
}
