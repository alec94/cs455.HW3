package cs455.Q8;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Alec on 4/10/2017.
 * gets state with highest percentage of people age > 85
 */

public class TopReducer extends Reducer<IntWritable, Text, Text, DoubleWritable> {
	public void reduce(IntWritable key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		ArrayList<String> states = new ArrayList<>();

		for (Text value : values) {
			states.add(value.toString());
		}

		Collections.sort(states);

		String[] parts = states.get(states.size() - 1).split("-");

		//System.out.println(Arrays.toString(parts));
		context.write(new Text(parts[1]), new DoubleWritable(Double.parseDouble(parts[0])));
	}
}
