package cs455.Q1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by Alec on 4/10/2017.
 * Reducer for Q1
 */
public class Q1Reducer extends Reducer<Text, Text, Text, Text> {
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		double owners = 0;
		double renters = 0;

		for (Text value : values) {
			String[] strings = value.toString().split("-");
			//System.out.println(Arrays.toString(strings));
			owners += Double.parseDouble(strings[0]);
			renters += Double.parseDouble(strings[1]);
		}

		double total = owners + renters;
		String result = "renters: " + (renters / total) * 100 + "%, owners: " + (owners / total) * 100 + "%";
		//System.out.println(key.toString() + ": " + result);
		context.write(new Text(key.toString() + ":"), new Text(result));
	}
}
