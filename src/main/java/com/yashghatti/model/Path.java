package com.yashghatti.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Path {

    private static int idSequence = 0;
    private List<Node> traversal;
    private String id;
    private Node target;

    public Path(List<Node> traversal, Node target) {
        this.id = "P"+(++idSequence);
        this.traversal = traversal;
        this.target = target;
    }

    private Path(){}

    public static Path from(Path existing, Node toNode){
        Path path = new Path();
        path.traversal = new ArrayList<>();
        path.id = "P"+(++idSequence);
        path.copyTraversal(existing.getTraversal());
        path.traversal.add(toNode);
        path.target = existing.target;
        return path;
    }

    public boolean hasTraversed(Node node){
        return traversal.contains(node);
    }

    public boolean hasTraversed(int x, int y){
        return traversal.contains(new Node(x, y, 0, 0));
    }

    public boolean hasReachedDestination() {
        return this.hasTraversed(target);
    }

    public List<Node> getAvailableRoutes() {
        return traversal.get(traversal.size() - 1).getAdjacentNodes()
                .stream()
                .filter(node -> !traversal.contains(node))
                .collect(Collectors.toList());
    }

    public boolean hasNoAvailableRoutes() {
        return getAvailableRoutes().size() == 0;
    }
    public List<Node> getTraversal() {
        return traversal;
    }

    private void copyTraversal(List<Node> oldTrav) {
        oldTrav.forEach(node -> {
            traversal.add(new Node(node.getX(), node.getY(), node.getXLimit(), node.getYLimit()));
        });
    }
    public Node getTarget() {
        return target;
    }

    public String getId() {
        return id;
    }
}
