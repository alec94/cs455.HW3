package cs455.Q3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
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
		Path outPath = new Path(args[1] + "/Q3");
		Configuration conf = new Configuration();
		FileSystem fileSystem = FileSystem.get(conf);

		Job job = Job.getInstance(conf);
		job.setJarByClass(Main.class);
		job.setMapperClass(Q3Mapper.class); //replace with correct mapper
		job.setReducerClass(Q3Reducer.class); //replace with correct reducer
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.setInputPaths(job, new Path(args[0])); //TODO: correct input file from the shared HDFS
		FileOutputFormat.setOutputPath(job, outPath);

		if (fileSystem.exists(outPath)) {
			fileSystem.delete(outPath, true);
		}

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}