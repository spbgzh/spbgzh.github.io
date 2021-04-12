package com.efimchick.ifmo;

import com.efimchick.ifmo.util.CourseResult;
import com.efimchick.ifmo.util.Person;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.reducing;

public class Collecting {
    public static int sum(IntStream a) {
        return a.reduce((f, s) -> f + s).getAsInt();
    }

    public static int production(IntStream a) {
        return a.reduce((f, s) -> f * s).getAsInt();
    }

    public static int oddSum(IntStream a) {
        return a.map(el -> el * Math.abs(el % 2)).reduce((f, s) -> f + s).getAsInt();
    }

    public static Map<Integer, Integer> sumByRemainder(int d, IntStream a) {
        Map<Integer, Integer> ostatiki = a.boxed().collect(Collectors.toMap(
                el -> (el % d),
                el -> el,
                (f, s) -> f + s
        ));
        return ostatiki;
    }

    //

    public static Map<Object, Object> totalScores(Stream<CourseResult> inpu) {
        List<CourseResult> inp = inpu.collect(Collectors.toList());
        return inp.stream().collect(Collectors.toMap(
                i -> i.getPerson(),
                i -> i.getTaskResults().keySet().stream().mapToInt(el -> i.getTaskResults().get(el)).summaryStatistics().getSum() / (double)inp.stream().flatMap(ii -> ii.getTaskResults().keySet().stream()).collect(Collectors.toSet()).size()
        ));
    }

    public static double averageTotalScore(Stream<CourseResult> inpu) {
        List<CourseResult> inp = inpu.collect(Collectors.toList());
        return inp.stream().map(i -> i.getTaskResults().keySet().stream().mapToInt(el -> i.getTaskResults().get(el)).summaryStatistics().getSum() / (double)inp.stream().flatMap(ii -> ii.getTaskResults().keySet().stream()).collect(Collectors.toSet()).size()).mapToDouble(el -> el).summaryStatistics().getAverage();
    }

    public static Map<String, Double> averageScoresPerTask(Stream<CourseResult> inpu) {
        List<CourseResult> inp = inpu.collect(Collectors.toList());
        return inp.stream().flatMap(ii -> ii.getTaskResults().keySet().stream()).collect(Collectors.toSet()).stream().collect(Collectors.toMap(
                i -> i,
                i -> inp.stream().map(el -> (el.getTaskResults().containsKey(i)) ? el.getTaskResults().get(i) : 0).mapToInt(el -> el).summaryStatistics().getAverage()
        ));
    }

    public static Object defineMarks(Stream<CourseResult> inpu) {
        Map tS = totalScores(inpu);
        return tS.keySet().stream().collect(Collectors.toMap(
                i -> i,
                i -> ((double)tS.get(i) > 90)? "A" : ((double)tS.get(i) >= 83)? "B" : ((double)tS.get(i) >= 75)? "C" : ((double)tS.get(i) >= 68)? "D" : ((double)tS.get(i) >= 60)? "E" : "F"
        ));
    }

    public static Object easiestTask(Stream<CourseResult> inpu) {
        Map aspt = averageScoresPerTask(inpu);
        double max = 0; Object maxka = null;
        for(Object u: aspt.keySet()) {
            if((double)aspt.get(u) > max) { max = (double)aspt.get(u); maxka = u; }
        }
        return maxka;
    }

    public static Collector<CourseResult, ?, String> printableStringCollector() {
        //String header = "Student\n";
        //return reducing(header, el -> el.getPerson().getLastName() + ' ' + el.getPerson().getFirstName() + ' ' + el.getTaskResults().keySet().stream().map(q -> el.getTaskResults().get(q).toString()).reduce((f, s) -> f + ' ' + s).get(), (a, b) -> a + b + '\n');
        return new dolg_verni();
    }

    private static class dolg_verni implements Collector<CourseResult, ArrayList<CourseResult>, String> {
        @Override
        public Supplier<ArrayList<CourseResult>> supplier() {
            return ArrayList<CourseResult>::new;
        }

        @Override
        public BiConsumer<ArrayList<CourseResult>, CourseResult> accumulator() {
            return (a, b) -> a.add(b);
        }

        @Override
        public BinaryOperator<ArrayList<CourseResult>> combiner() {
            return null; // combiner is not needed here
        }

        @Override
        public Function<ArrayList<CourseResult>, String> finisher() {
            return i -> {
                Map<String, Double> tmp = averageScoresPerTask(i.stream());
                List<String> predmeti = tmp.keySet().stream().sorted().collect(Collectors.toList());
                List<Person> studiki = i.stream().map(el -> el.getPerson()).sorted((f, s) -> f.getLastName().compareTo(s.getLastName())).collect(Collectors.toList());
                Map mrks = totalScores(i.stream());
                int maxlen = (int)i.stream().map(el -> el.getPerson()).mapToInt(el -> el.getFirstName().length() + el.getLastName().length()).summaryStatistics().getMax() + 1;
                String frline = "Student" + " ".repeat(maxlen - "Student".length()) + " | " + tmp.keySet().stream().sorted().reduce((f, s) -> f.toString() + " | " + s.toString()).get() + " | Total | Mark |" + '\n';
                String otlines = "";
                Map<Person, Map> ocenochki = i.stream().collect(Collectors.toMap(
                        y -> y.getPerson(),
                        y -> y.getTaskResults()
                ));
                for(Person u: studiki) {
                    String studik = u.getLastName() + ' ' + u.getFirstName();
                    otlines += studik + " ".repeat(maxlen - studik.length()) + " | ";
                    for(String subj: predmeti) {
                        String ocenka;
                        if(ocenochki.get(u).containsKey(subj)) ocenka = Integer.toString((int)ocenochki.get(u).get(subj));
                        else ocenka = "0";
                        otlines += " ".repeat(subj.length() - ocenka.length()) + ocenka + " | ";
                    }
                    otlines += String.format("%.2f",totalScores(i.stream()).get(u)).replace(',', '.') + " | ";
                    Map marki = (Map)defineMarks(i.stream());
                    otlines += "   " + marki.get(u) + " |";
                    otlines += '\n';
                }
                otlines += "Average" + " ".repeat(maxlen - "Average".length()) + " | ";
                for(String subj: predmeti) {
                    //String ball = Double.toString();
                    otlines += " ".repeat(subj.length() - String.format("%.2f", averageScoresPerTask(i.stream()).get(subj)).length()) + String.format("%.2f", averageScoresPerTask(i.stream()).get(subj)).replace(',', '.') + " | ";
                }
                Double ats = averageTotalScore(i.stream());
                otlines += String.format("%.2f", ats).replace(',', '.') + " |    ";
                otlines += (ats > 90)? "A" : (ats >= 83)? "B" : (ats >= 75)? "C" : (ats >= 68)? "D" : (ats >= 60)? "E" : "F";
                otlines += " |";
                return frline + otlines;
            };
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Set.of(Characteristics.CONCURRENT);
        }
    }
}
