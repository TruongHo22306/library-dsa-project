package org.example.library.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class GenericAlgorithm {
    public static <T> List<T> search(List<T> items, String keyword, Function<T, String> keyExtractor) {
        List<T> sortedItems = new ArrayList<>(items);
        sortedItems.sort(Comparator.comparing(keyExtractor));

        List<T> results = new ArrayList<>();
        int left = 0, right = sortedItems.size() - 1;
        String lowerCaseKeyword = keyword.toLowerCase();

        while (left <= right) {
            int mid = left + (right - left) / 2;
            T midItem = sortedItems.get(mid);
            String midValue = keyExtractor.apply(midItem).toLowerCase();

            if (midValue.contains(lowerCaseKeyword)) {
                results.add(midItem);

                int temp = mid;
                while (--temp >= left && keyExtractor.apply(sortedItems.get(temp)).toLowerCase().contains(lowerCaseKeyword)) {
                    results.add(sortedItems.get(temp));
                }
                temp = mid;
                while (++temp <= right && keyExtractor.apply(sortedItems.get(temp)).toLowerCase().contains(lowerCaseKeyword)) {
                    results.add(sortedItems.get(temp));
                }
                break;
            }

            if (midValue.compareTo(lowerCaseKeyword) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return results;
    }


    public static <T> List<T> quickSort(List<T> items, BiFunction<T, T, Integer> comparator, boolean ascending) {
        List<T> sortedItems = new ArrayList<>(items);
        quickSortRecursive(sortedItems, 0, sortedItems.size() - 1, comparator, ascending);
        return sortedItems;
    }

    private static <T> void quickSortRecursive(List<T> list, int low, int high,
                                               BiFunction<T, T, Integer> comparator, boolean ascending) {
        if (low < high) {
            int partitionIndex = partition(list, low, high, comparator, ascending);

            quickSortRecursive(list, low, partitionIndex - 1, comparator, ascending);
            quickSortRecursive(list, partitionIndex + 1, high, comparator, ascending);
        }
    }

    private static <T> int partition(List<T> list, int low, int high,
                                     BiFunction<T, T, Integer> comparator, boolean ascending) {
        T pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            int comparisonResult = comparator.apply(list.get(j), pivot);

            if ((ascending && comparisonResult <= 0) || (!ascending && comparisonResult >= 0)) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }

    private static <T> void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
