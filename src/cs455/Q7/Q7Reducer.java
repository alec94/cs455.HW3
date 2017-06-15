package cs455.Q7;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by Alec on 4/10/2017.
 * Reducer for Q1
 */
public class Q7Reducer extends Reducer<Text, Text, Text, DoubleWritable> {
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		double houseTotal = 0;
		double roomTotal = 0;

		for (Text value : values) {
			String[] parts = value.toString().split("-");

			houseTotal += Integer.parseInt(parts[0]);
			roomTotal += Integer.parseInt(parts[1]);
		}

		//System.out.println(key.toString() + "- houseTotal: " + houseTotal + ", roomTotal: " + roomTotal +", average rooms per house: " + roomTotal/houseTotal);

		context.write(key, new DoubleWritable(roomTotal / houseTotal));
	}
}
