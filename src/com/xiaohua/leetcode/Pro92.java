package com.xiaohua.leetcode;

/**
 * @auther XIAOHUA
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/reverse-linked-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/7/2 0:15
 */
public class Pro92 {
    public static void main(String[] args) {
        ListNode listnode3 = new ListNode(1);
        listnode3.next=new ListNode(2);
        listnode3.next.next=new ListNode(3);

        System.out.println(new Pro92().reverseBetween(listnode3,1,2));
    }


    public ListNode reverseBetween(ListNode head, int left, int right) {
        //头结点可能有变化，因此弄个虚拟头结点
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next=head;
        ListNode pre=dummyNode;
        //左结点
        for (int i = 0; i < left-1; i++) {
            pre=pre.next;
        }
        ListNode rightNode=pre,leftNode=pre.next;

        for (int i = 0; i < right-left; i++) {
            rightNode=rightNode.next;
        }
        ListNode curNode=rightNode.next;
        pre.next=null;
        rightNode.next=null;
        reverseLinkedList(leftNode);
        //拼接
        pre.next=rightNode;
        leftNode.next=curNode;
        return dummyNode.next;
    }

    private void reverseLinkedList(ListNode head) {
        ListNode pre=null,cur=head;
        while (cur!=null){
            ListNode temp=cur.next;
            cur.next=pre;
            pre=cur;
            cur=temp;
        }
    }
}
