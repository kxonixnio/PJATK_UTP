/**
 *
 *  @author Tomczyk Mikołaj S22472
 *
 */

import java.util.*;

public class Main {
    public Main() {
        List<Integer> src1 = Arrays.asList(1, 7, 9, 11, 12);/*<-- tu dopisa� inicjacj� element�w listy */
        System.out.println(test1(src1));

        List<String> src2 = Arrays.asList("a", "zzzz", "vvvvvvv");/*<-- tu dopisa� inicjacj� element�w listy */
        System.out.println(test2(src2));
    }

    public List<Integer> test1(List<Integer> src) {
        Selector<Integer> sel = new Selector<Integer>() {
            @Override
            public boolean select(Integer selected) {
                return selected < 10;
            }
        }; /*<-- definicja selektora; bez lambda-wyra�e�; nazwa zmiennej sel */
        Mapper<Integer, Integer> map = new Mapper<Integer, Integer>() {
            @Override
            public Integer map(Integer mapping) {
                return mapping + 10;
            }
        };   /*<-- definicja mappera; bez lambda-wyra�e�; nazwa zmiennej map */
        return ListCreator.collectFrom(src).when(sel).mapEvery(map);  /*<-- zwrot wyniku
      uzyskanego przez wywo�anie statycznej metody klasy ListCreator:
     */
    }

    public List<Integer> test2(List<String> src) {
        Selector<String> sel = new Selector<String>() {
            @Override
            public boolean select(String selected) {
                return selected.length() > 3;
            }
        };/*<-- definicja selektora; bez lambda-wyra�e�; nazwa zmiennej sel */
        Mapper<Integer, String> map = new Mapper<Integer, String>() {
            @Override
            public Integer map(String mapping) {
                return mapping.length() + 10;
            }
        };   /*<-- definicja mappera; bez lambda-wyra�e�; nazwa zmiennej map */
        return ListCreator.collectFrom(src).when(sel).mapEvery(map);  /*<-- zwrot wyniku
      uzyskanego przez wywo�anie statycznej metody klasy ListCreator:
     */
    }

    public static void main(String[] args) {
        new Main();
    }
}