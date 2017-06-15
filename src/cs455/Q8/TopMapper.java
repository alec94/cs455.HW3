package cs455.Q8;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by Alec on 4/10/2017.
 * gets state with top percentage
 */
public class TopMapper extends Mapper<Object, Text, IntWritable, Text> {
	private static IntWritable one = new IntWritable(1);

	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {

		String[] parts = value.toString().split("\\s+");
		//System.out.println(value.toString());
		context.write(one, new Text(parts[1] + "-" + parts[0]));
	}
}
