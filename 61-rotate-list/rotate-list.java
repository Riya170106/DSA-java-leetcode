/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null||head.next==null||k==0)
        {
            return head;
        }
        int l=0;
        ListNode curr=head;
        while(curr!=null)
        {
            curr=curr.next;
            l++;
        }
         k=k%l;
         if(k==0)
         {
            return head;
         }
         ListNode fast=head;
         for(int i=0;i<k;i++)
         {
            fast=fast.next;
         }
         ListNode slow=head;
         while(fast.next!=null)
         {
            slow=slow.next;
            fast=fast.next;
         }
         fast.next=head;
         head=slow.next;
         slow.next=null;
         return head;
    }
}