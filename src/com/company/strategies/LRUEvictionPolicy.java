package com.company.strategies;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key>{

    private class Node {
        Key key;
        Node next,prev;
        public Node(Key key) {
            this.key = key;
        }
    }
    Node head,tail;
    Map<Key,Node> keyNodeMap = new HashMap<>();

    @Override
    public void accessKey(Key key) {
      if(keyNodeMap.containsKey(key)) {
          detachNode(keyNodeMap.get(key));
      }
      Node node = new Node(key);
      attachAtEnd(node);
      keyNodeMap.put(key,node);
    }

    @Override
    public Key evictKey() {
        Key key = head.key;
        detachNode(head);
        keyNodeMap.remove(key);
        return key;
    }

    private void attachAtEnd(Node node) {
        if(tail == null) {
            head = node;
            tail = node;
            return;
        }
        tail.next = node;
        node.prev = tail;
        tail = tail.next;
    }

    private void detachNode(Node node) {
        if(node != head)
            node.prev.next = node.next;
        else
            head = node.next;
        if(node != tail)
            node.next.prev = node.prev;
        else
            tail = node.prev;
    }
}
