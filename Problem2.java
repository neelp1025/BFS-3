// Time Complexity : O(n) where n is the number of unique nodes
// Space Complexity : O(n) for storing the visited set of nodes
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach

/**
 * Starting from the node given, create a clone of the node and save the entry of original->new node in the map.
 * For current nodes neighbors, create the replica nodes and add it to the map. Also, add the original neighbors in a queue so that their neighbor nodes can be set in the cloned graph.
 */
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;

        Map<Node, Node> visited = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        visited.put(node, new Node(node.val, new ArrayList<>()));

        while (!q.isEmpty()) {
            Node curr = q.poll();
            for (Node neighbor : curr.neighbors) {
                // if the neighbor node is encountered first time, create its clone node
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    q.add(neighbor);
                }

                // create the neighbor links in new cloned node
                visited.get(curr).neighbors.add(visited.get(neighbor));
            }
        }

        // return new graph's cloned entry point
        return visited.get(node);
    }
}