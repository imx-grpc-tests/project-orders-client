
package com.max.grpc.orders.client;

import com.max.grpc.orders.proto.OrderReceipt;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class LogUtils {
    public static String printReceipt(OrderReceipt receipt) {
        int maxTitleLength = receipt.getItemsList().stream()
            .mapToInt(item -> item.getTitle().length()).max().getAsInt();

        int maxPriceLength = receipt.getItemsList().stream()
            .mapToInt(item -> String.valueOf(item.getPrice()).length()).max().getAsInt();

        var index = new AtomicInteger();
        String orderItems = receipt.getItemsList().stream().map(item -> String.format(
            "Item #%d : %s : %s : %s $",
            index.incrementAndGet(),
            item.getId(),
            padEnd(item.getTitle(), maxTitleLength),
            padStart(String.valueOf(item.getPrice()), maxPriceLength)
        )).collect(Collectors.joining("\n\t\t"));

        var date = Date.from(Instant.ofEpochSecond(receipt.getDate()));
        var dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String parsedDate = dateFormat.format(date);

        return new StringBuilder()
            .append("\n\t* ID: ")
            .append(receipt.getId())
            .append("\n\t* Order date: ")
            .append(parsedDate)
            .append("\n\t* Total price: ")
            .append(receipt.getTotalPrice())
            .append(" $\n\t\t")
            .append(orderItems)
            .toString();
    }

    public static String padStart(String str, int outLength) {
        if (outLength < str.length()) return str;
        return " ".repeat(outLength - str.length()) + str;
    }

    public static String padEnd(String str, int outLength) {
        if (outLength < str.length()) return str;
        return str + " ".repeat(outLength - str.length());
    }
}
