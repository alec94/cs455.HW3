package cs455.Q8;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException, ClassNotFoundException,
			InterruptedException {
		if (args.length < 2) {
			System.out.printf("Usage: <input file> <output dir>\n");
			System.exit(-1);
		}

		Path outPath = new Path(args[1] + "/Q8");
		Path tempPath = new Path(args[1] + "/temp/Q8");

		Configuration conf = new Configuration();
		FileSystem fileSystem = FileSystem.get(conf);

		Job job = Job.getInstance(conf);
		job.setJarByClass(Main.class);
		job.setMapperClass(Q8Mapper.class); //replace with correct mapper
		job.setReducerClass(Q8Reducer.class); //replace with correct reducer
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, tempPath);

		if (fileSystem.exists(tempPath)) {
			fileSystem.delete(tempPath, true);
		}

		job.waitForCompletion(true);

		Job job1 = Job.getInstance(conf);
		job1.setJarByClass(Main.class);
		job1.setMapperClass(TopMapper.class); //replace with correct mapper
		job1.setReducerClass(TopReducer.class); //replace with correct reducer
		job1.setOutputKeyClass(IntWritable.class);
		job1.setOutputValueClass(Text.class);
		job1.setInputFormatClass(TextInputFormat.class);
		job1.setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.setInputPaths(job1, tempPath + "/part-r-00000");
		FileOutputFormat.setOutputPath(job1, outPath);

		if (fileSystem.exists(outPath)) {
			fileSystem.delete(outPath, true);
		}

		System.exit(job1.waitForCompletion(true) ? 0 : 1);
	}
}