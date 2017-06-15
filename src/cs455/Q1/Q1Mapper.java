package cs455.Q1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by Alec on 4/10/2017.
 * Mapper for Q1
 */
public class Q1Mapper extends Mapper<Object, Text, Text, Text> {
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {

		int summaryLevel = Integer.parseInt(value.toString().substring(10, 13));
		int segment = Integer.parseInt(value.toString().substring(24, 28));

		if (summaryLevel == 100 && segment == 2) {
			int owner = Integer.parseInt(value.toString().substring(1803, 1812));
			int renter = Integer.parseInt(value.toString().substring(1812, 1821));
			String state = value.toString().substring(8, 10);
			//System.out.println("owner: " + owner + ", renter: " + renter);
			context.write(new Text(state), new Text(owner + "-" + renter));
		}
	}
}
