package cs455.Q4;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by Alec on 4/10/2017.
 * Reducer for Q1
 */
public class Q4Reducer extends Reducer<Text, Text, Text, Text> {
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		double urban = 0;
		double rural = 0;
		double undefined = 0;

		//urban + "-" + rural + "-" + undefined
		for (Text value : values) {
			String[] parts = value.toString().split("-");

			urban += Double.parseDouble(parts[0]);
			rural += Double.parseDouble(parts[1]);
			undefined += Double.parseDouble(parts[2]);
		}

		double total = urban + rural + undefined;

		String result = "urban: " + (urban / total) * 100 + "%, rural: " + (rural / total) * 100 + "%, undefined: " + (undefined / total) * 100 + "%";
		//System.out.println(key.toString() + ": " + result);
		context.write(new Text(key.toString() + ":"), new Text(result));
	}
}
