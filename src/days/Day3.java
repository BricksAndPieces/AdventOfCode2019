package days;

import util.InputReader;

import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;

public class Day3 {

    public static void main(String[] args) {
        String[] rawInput = InputReader.getInput(3).split("\n");
        String[] dir1 = rawInput[0].split(",");
        String[] dir2 = rawInput[1].split(",");

        System.out.println("Part 1: " + part1(dir1, dir2));
        System.out.println("Part 2: " + part2(dir1, dir2));
    }

    private static int part1(String[] dir1, String[] dir2) {
        Set<Point> line1 = getLine(dir1);
        Set<Point> line2 = getLine(dir2);

        int min = Integer.MAX_VALUE;
        Set<Point> matching = line1.stream().filter(line2::contains).collect(Collectors.toSet());
        for(Point p : matching)
            min = Math.min(min, Math.abs(p.x) + Math.abs(p.y));

        return min;
    }

    private static int part2(String[] dir1, String[] dir2) {
        Set<Combo> line1 = getLineWithSteps(dir1);
        Set<Combo> line2 = getLineWithSteps(dir2);

        System.out.println(line1.size());

        Set<Integer> matching = new HashSet<>();
        line1.forEach(combo1 -> matching.addAll(line2.stream().filter(combo1::equals)
             .map(combo2 -> combo1.steps + combo2.steps).collect(Collectors.toSet())));

        int min = Integer.MAX_VALUE;
        for(Integer step : matching)
            min = Math.min(min, step);

        return min;
    }

    private static Set<Point> getLine(String[] directions) {
        int x = 0, y = 0;
        Set<Point> line = new HashSet<>();
        for(String s : directions) {
            for(int i = 0; i < Integer.valueOf(s.substring(1)); i++) {
                switch(s.charAt(0)) {
                    case 'U' : line.add(new Point(x, ++y)); break;
                    case 'D' : line.add(new Point(x, --y)); break;
                    case 'L' : line.add(new Point(--x, y)); break;
                    case 'R' : line.add(new Point(++x, y)); break;
                }
            }
        }

        return line;
    }

    private static Set<Combo> getLineWithSteps(String[] directions) {
        int x = 0, y = 0, steps = 0;
        Set<Combo> line = new HashSet<>();
        for(String s : directions) {
            for(int i = 0; i < Integer.valueOf(s.substring(1)); i++) {
                switch(s.charAt(0)) {
                    case 'U' : line.add(new Combo(new Point(x, ++y), ++steps)); break;
                    case 'D' : line.add(new Combo(new Point(x, --y), ++steps)); break;
                    case 'L' : line.add(new Combo(new Point(--x, y), ++steps)); break;
                    case 'R' : line.add(new Combo(new Point(++x, y), ++steps)); break;
                }
            }
        }

        return line;
    }

    private static class Combo {

        private final Point point;
        private final int steps;

        private Combo(Point point, int steps) {
            this.point = point;
            this.steps = steps;
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Combo)) return false;
            return point.equals(((Combo) obj).point);
        }
    }
}