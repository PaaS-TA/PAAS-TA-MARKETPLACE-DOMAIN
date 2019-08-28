package org.openpaas.paasta.marketplace.api.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Stats<ID, V extends Number> {

    private static final String PATTERN = "yyyy-MM-dd";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    @Id
    private Long id;

    public static String toString(LocalDateTime value) {
        return FORMATTER.format(value);
    };

    public static LocalDateTime toDate(String value) {
        return LocalDateTime.parse(value, FORMATTER);
    };

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Value<ID, V extends Number> implements Comparable<Value<ID, V>> {

        private ID id;

        private V value;

        @Override
        public int compareTo(Value<ID, V> o) {
            if (value == o.getValue()) {
                return 0;
            }
            if (value.doubleValue() < o.getValue().doubleValue()) {
                return -1;
            } else {
                return 1;
            }
        }
    };

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Term {

        private LocalDateTime start;

        private LocalDateTime end;

    };

    public static List<Term> termsOf(LocalDateTime epoch, int numberOfBefore, ChronoUnit unit) {
        if (!(numberOfBefore > 0)) {
            throw new IllegalArgumentException("the value of 'numberOfBefore' must bigger than 0. but was '" + numberOfBefore + "'.");
        }
        if (!(unit == ChronoUnit.MONTHS || unit == ChronoUnit.DAYS)) {
            throw new IllegalArgumentException("the unit must be '" + ChronoUnit.MONTHS + "', or '" + ChronoUnit.DAYS + "'.");
        }

        if (epoch == null) {
            epoch = LocalDateTime.now();
        }

        int year = epoch.getYear();
        int month = epoch.getMonthValue();
        int day = epoch.getDayOfMonth();
        if (unit == ChronoUnit.MONTHS) {
            day = 1;
        }
        if (unit == ChronoUnit.DAYS) {
        }

        LocalDateTime base = LocalDateTime.of(year, month, day, 0, 0);

        List<Term> terms = new ArrayList<>();
        for (int i = 0; i < numberOfBefore; i++) {
            LocalDateTime s = base.minus(i, unit);
            LocalDateTime e = base.minus(i - 1, unit);

            terms.add(new Term(s, e));
        }
        Collections.reverse(terms);

        return terms;
    }

}
