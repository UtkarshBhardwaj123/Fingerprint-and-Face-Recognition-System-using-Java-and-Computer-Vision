package com.vscode.recognizeface;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.bytedeco.opencv.global.opencv_core;
import org.datavec.image.loader.NativeImageLoader;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.nn.transferlearning.TransferLearningHelper;
import org.deeplearning4j.zoo.PretrainedType;
import org.deeplearning4j.zoo.ZooModel;
import org.deeplearning4j.zoo.model.VGG16;
import org.nd4j.common.loader.Loader;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.VGG16ImagePreProcessor;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.nativeblas.Nd4jBlas;

public class InputVector {

//	private double[] vector;

	private TransferLearningHelper transferLearningHelper;
	private NativeImageLoader nativeImageLoader;
	private DataNormalization preProcessor;

//	public double[] getVector() {
//		return vector;
//	}

	public InputVector() throws IOException, InterruptedException {

		// loading DL4J
//	DataSet
//		try {
//		    Loader.load(Nd4jBlas.class);
//		} catch (UnsatisfiedLinkError e) {
//		    String path = Loader.cacheResource(Nd4jBlas.class, "C:/Users/Utkarsh Bhardwaj/.javacpp/cache/cuda-11.0-8.0-1.5.4-windows-x86_64.jar/org/bytedeco/cuda/windows-x86_64/jnicudart.dll").getPath();
//		    new ProcessBuilder("D:\\Courses\\Dependencies Builder\\DependenciesGui.exe", path).start().waitFor();
//		}

		ZooModel zooModel = VGG16.builder().build();
		ComputationGraph obj = (ComputationGraph) zooModel.initPretrained(PretrainedType.VGGFACE);

		System.out.println("DL4J loaded");

		transferLearningHelper = new TransferLearningHelper(obj, "pool4");
		nativeImageLoader = new NativeImageLoader(224, 224, 3);
		preProcessor = new VGG16ImagePreProcessor();

	}

	public double[] createInputVector(BufferedImage img) {

		INDArray imgMatrix;
		try {
			imgMatrix = nativeImageLoader.asMatrix(img);
			preProcessor.transform(imgMatrix);
			DataSet dataSet = new DataSet(imgMatrix, Nd4j.create(new float[] { 0, 0 }));
			DataSet featurizedDataSet = transferLearningHelper.featurize(dataSet);
			INDArray extractedFeatures = featurizedDataSet.getFeatures();
			long reshapeDimension = 1;
			for (long dimension : extractedFeatures.shape()) {
				reshapeDimension *= dimension;
			}
			extractedFeatures = extractedFeatures.reshape(1, reshapeDimension);
			return extractedFeatures.data().asDouble();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

}
