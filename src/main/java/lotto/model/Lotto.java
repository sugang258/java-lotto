package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
//    private final List<Integer> numbers;
//
//    public Lotto(List<Integer> numbers) {
//        validate(numbers);
//        this.numbers = numbers;
//    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    public List<String> change(String str) {
        List<String> num = new ArrayList<>();
        String[] splitStr = str.split(",");
        for (int i = 0; i < splitStr.length; i++) {
            num.add(splitStr[i]);
        }
        System.out.println(num);
        return num;
    }

}
