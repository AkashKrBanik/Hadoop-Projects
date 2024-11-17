package org.akb;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WL_Reducer extends Reducer<Text, Text, Text, Text> {
    private int minLength = Integer.MAX_VALUE;
    private int maxLength = Integer.MIN_VALUE;
    private String minWord = "";
    private String maxWord = "";

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            String[] parts = value.toString().split("\t");
            String word = parts[0];
            int length = Integer.parseInt(parts[1]);

            if (length < minLength) {
                minLength = length;
                minWord = word;
            }
            if (length > maxLength) {
                maxLength = length;
                maxWord = word;
            }
        }

        // Output the results in the desired format
        context.write(new Text("Minimum Word Length:"), new Text(String.valueOf(minLength)));
        context.write(new Text("And Minimum Word is:"), new Text(minWord));
        context.write(new Text("Maximum Word Length:"), new Text(String.valueOf(maxLength)));
        context.write(new Text("And Maximum Word is:"), new Text(maxWord));
    }
}
