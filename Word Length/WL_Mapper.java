package org.akb;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class WL_Mapper extends Mapper<Object, Text, Text, Text> {
    private final static Text lengthKey = new Text("length");

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        StringTokenizer tokenizer = new StringTokenizer(value.toString());
        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken();
            int wordLength = word.length();
            context.write(lengthKey, new Text(word + "\t" + wordLength));
        }
    }
}
