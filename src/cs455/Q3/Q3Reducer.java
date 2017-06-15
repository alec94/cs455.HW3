package cs455.Q3;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by Alec on 4/10/2017.
 * Reducer for Q1
 */
public class Q3Reducer extends Reducer<Text, Text, Text, Text> {
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		double male18 = 0;
		double male29 = 0;
		double male39 = 0;
		double maleTotal = 0;
		double female18 = 0;
		double female29 = 0;
		double female39 = 0;
		double femaleTotal = 0;

		for (Text value : values) {
			String[] parts = value.toString().split("-");
			male18 += Double.parseDouble(parts[0]);
			male29 += Double.parseDouble(parts[1]);
			male39 += Double.parseDouble(parts[2]);
			maleTotal += Double.parseDouble(parts[3]);
			female18 += Double.parseDouble(parts[4]);
			female29 += Double.parseDouble(parts[5]);
			female39 += Double.parseDouble(parts[6]);

			if (!parts[7].equals("")) { // for some reason femaleTotal is coming through as a blank string randomly
				femaleTotal += Double.parseDouble(parts[7]);
			}
		}

		String result = "Males: under 18: " + (male18 / maleTotal) * 100 + "%, 19 - 29: " + (male29 / maleTotal) * 100 + "%, 30-39: " + (male39 / maleTotal) * 100 + "%.";
		result += "\n\tFemales: under 18: " + (female18 / femaleTotal) * 100 + "%, 19 - 29: " + (female29 / femaleTotal) * 100 + "%, 30-39: " + (female39 / femaleTotal) * 100 + "%.";

		//System.out.println(result);

		context.write(new Text(key.toString() + ":"), new Text(result));
	}
}
