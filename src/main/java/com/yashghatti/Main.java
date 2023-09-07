package com.yashghatti;

import com.yashghatti.model.Grid;
import com.yashghatti.model.Node;
import com.yashghatti.model.Path;

import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        Grid threeByThree = Grid.fromDimensions(5, 5, 1, 1, 4, 1);
        List<Path> calculatedPaths = threeByThree
                .calculatePaths()
                .stream().sorted(
                        Comparator.comparing(path -> path.getTraversal().size())
                )
                .toList();

        System.out.println("-------------------");
        System.out.print("Path with the most steps : ");
        calculatedPaths.stream()
                        .filter(cp -> cp.getTraversal().size() == calculatedPaths.get(calculatedPaths.size() - 1).getTraversal().size())
//                        .findFirst()
//                        .stream()
//                        .toList()
                        .forEach(cp -> {
                            printPath(cp);
                            printRoute(cp);
                        });
        System.out.println("Total Paths : "+calculatedPaths.size());
        System.out.print("Path with the least steps : ");
        printPath(calculatedPaths.get(0));
        printRoute(calculatedPaths.get(0));
    }

    public static void printPath(Path path) {
        System.out.println("Path - "+path.getId()+" ["+path.getTraversal().size()+"] "
                +(path.hasReachedDestination()?"-CMP-":"") + (path.hasNoAvailableRoutes()?"-NAR-":"") );
        System.out.println(path.getTraversal()
                .stream()
                .map(node -> node.getX()+","+node.getY())
                .collect(Collectors.joining(" -> ")));
    }

    public static void printRoute(Path path) {
        int ceilX = path.getTraversal().get(0).getXLimit();
        int ceilY = path.getTraversal().get(0).getYLimit();
        List<Node> trav =  path.getTraversal();
        for(int y=1; y<= ceilY; y++){
            for(int x=1; x<=ceilX; x++){
                final int x1= x;
                final int y1= y;
                if(path.hasTraversed(x,y)){
                    Node curr = trav.stream().filter(node -> node.getX()==x1 && node.getY()==y1).findFirst().get();
                    Node next = null;
                    if(trav.indexOf(curr) < trav.size()-1) next =  trav.get(trav.indexOf(curr)+1);
                    if(next == null)
                        System.out.print("x ");
                    else if(next.getX() > curr.getX())
                        System.out.print("> ");
                    else if(next.getX() < curr.getX())
                        System.out.print("< ");
                    else if(next.getY() > curr.getY())
                        System.out.print("v ");
                    else if(next.getY() < curr.getY())
                        System.out.print("^ ");
                    else
                        System.out.println("o");
                } else {
                    System.out.print(". ");
                }

            }
            System.out.println();
        }
    }
}