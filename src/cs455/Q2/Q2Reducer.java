package cs455.Q2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by Alec on 4/10/2017.
 * Reducer for Q1
 */
public class Q2Reducer extends Reducer<Text, Text, Text, Text> {
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		double totalMales = 0;
		double totalFemales = 0;
		double totalnmMales = 0;
		double totalnmFemales = 0;

		//males + "-" + nmMales + "-" + females + "-" + nmFemales
		for (Text value : values) {
			String[] strings = value.toString().split("-");
			//System.out.println(Arrays.toString(strings));
			totalMales += Double.parseDouble(strings[0]);
			totalnmMales += Double.parseDouble(strings[1]);
			totalFemales += Double.parseDouble(strings[2]);
			totalnmFemales += Double.parseDouble(strings[3]);
		}

		String result = "Never married males: " + (totalnmMales / totalMales) * 100 + "%, Never married females: " + (totalnmFemales / totalFemales) * 100 + "%";

		//System.out.println(key.toString() + ": " + result);

		context.write(new Text(key.toString() + ":"), new Text(result));

	}
}
