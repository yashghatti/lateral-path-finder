package com.yashghatti.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Grid {

    private int length;
    private int width;

    private Node start;
    private Node end;

    private List<Path> paths = new ArrayList<>();

    private Grid(){};

    public static Grid fromDimensions(int length, int width, int startX, int startY, int endX, int endY){
        Grid grid = new Grid();
        grid.length = length;
        grid.width = width;
        grid.start = new Node(startX, startY, length, width);
        grid.end = new Node(endX, endY, length, width);
        List<Node> initialTraversal = new ArrayList<>();
        initialTraversal.add(grid.start);
        grid.paths.add(new Path(initialTraversal, grid.end));
        return grid;
    }

    public boolean allPathsHaveCompleted() {
        return paths.stream()
                .allMatch(path -> path.hasReachedDestination() || path.hasNoAvailableRoutes());
    }

    public List<Path> calculatePaths() {
        while(!allPathsHaveCompleted()) {;
//            Scanner sn = new Scanner(System.in);
            for(int i=0; i<paths.size(); i++) {
                Path path = paths.get(i);
                if(path.hasReachedDestination() || path.hasNoAvailableRoutes())
                    continue;
                Node n = path.getTraversal().get(path.getTraversal().size() - 1);
//                System.out.println("From Path - " + path.getId()+" At: ["+n.getY()+","+n.getY()+"] Route: "+
//                    path.getTraversal().stream().map(node -> "("+node.getX()+","+node.getY()+")").collect(Collectors.joining(" -> "))
//                );
                List<Node> routes = path.getAvailableRoutes();
//                System.out.println("Available Routes - "+routes);
                if (routes.size() == 1) {
                    if(!path.hasReachedDestination())
                        path.getTraversal().add(routes.get(0));
                } else if (routes.size() > 1){
                    if(!path.hasReachedDestination()) {
                        for (int j = 1; j < routes.size(); j++) {
                            paths.add(Path.from(path, routes.get(j)));
                        }
                        path.getTraversal().add(routes.get(0));
                    }
                }
//                System.out.println("Total paths -- "+paths.stream().map(Path::getId).collect(Collectors.joining(",")));
            }
//            System.out.println("Pause....");
//            sn.nextLine();
        }
        return paths;
    }

    public List<Path> getPaths() {
        return paths;
    }
}
