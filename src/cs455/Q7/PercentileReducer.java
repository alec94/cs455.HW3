package cs455.Q7;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Alec on 4/10/2017.
 * gets the 95th percentile for average number of rooms per state.
 */
public class PercentileReducer extends Reducer<IntWritable, Text, Text, DoubleWritable> {
	public void reduce(IntWritable key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		ArrayList<String> rooms = new ArrayList<>();

		for (Text value : values) {
			rooms.add(value.toString());
		}

		Collections.sort(rooms);

		int n = (int) Math.floor(.95 * rooms.size()) - 1;

		String[] parts = rooms.get(n).split("-");
		//System.out.println(Arrays.toString(rooms.toArray()));
		//System.out.println(rooms.get(n));
		context.write(new Text(parts[1]), new DoubleWritable(Double.parseDouble(parts[0])));
	}
}
