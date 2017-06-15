package cs455.Q9;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by Alec on 4/10/2017.
 * get number of rooms per person on a per state basis
 */
public class Q9Reducer extends Reducer<Text, Text, Text, DoubleWritable> {
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		double totalPop = 0;
		double totalRooms = 0;

		for (Text value : values) {
			String[] parts = value.toString().split("-");
			if (parts[0].equals("P")) {
				totalPop += Double.parseDouble(parts[1]);
			} else if (parts[0].equals("R")) {
				totalRooms += Double.parseDouble(parts[1]);
			}
		}

		context.write(key, new DoubleWritable(totalPop / totalRooms));
	}
}
