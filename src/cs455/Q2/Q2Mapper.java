package cs455.Q2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by Alec on 4/10/2017.
 * Mapper for Q1
 */
public class Q2Mapper extends Mapper<Object, Text, Text, Text> {
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {

		int summaryLevel = Integer.parseInt(value.toString().substring(10, 13));
		int segment = Integer.parseInt(value.toString().substring(24, 28));

		if (summaryLevel == 100 && segment == 1) {
			String state = value.toString().substring(8, 10);
			int males = Integer.parseInt(value.toString().substring(363, 372));
			int females = Integer.parseInt(value.toString().substring(372, 381));
			int nmMales = Integer.parseInt(value.toString().substring(4422, 4431));
			int nmFemales = Integer.parseInt(value.toString().substring(4467, 4476));

			//System.out.println(state + ":\t" + males + "-" + nmMales + "-" + females + "-" + nmFemales );
			context.write(new Text(state), new Text(males + "-" + nmMales + "-" + females + "-" + nmFemales));
		}
	}
}
