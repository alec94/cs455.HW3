package cs455.Q8;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by Alec on 4/10/2017.
 * Reducer for Q1
 */
public class Q8Reducer extends Reducer<Text, Text, Text, DoubleWritable> {
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		double totalPop = 0;
		double over85 = 0;

		for (Text value : values) {
			String[] parts = value.toString().split("-");

			totalPop += Double.parseDouble(parts[0]);
			over85 += Double.parseDouble(parts[1]);
		}

		context.write(key, new DoubleWritable(over85 / totalPop * 100));
	}
}
