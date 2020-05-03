package com.msys.soaptest;

import java.util.HashMap;
import java.util.Random;

public class MaxUniqueStringLength {

	private static HashMap<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
	private static Integer jobId= null;

	public String getMaxUniqueStringLength(String commaSepratedString) {
		String[] input = commaSepratedString.toLowerCase().split(","); 
		boolean isDuplicateCharExist = false;
		String result = "";
		if (null != input) {
			if(input.length> 0 && input.length<8){
				try{
					for (int i = 0; i < input.length; i++) {
						if (resultMap.containsKey(input[i])) {
							// if the element is already exist in the map then skip the
							// element
							continue;
						} else {
							isDuplicateCharExist = checkDuplicateCharExist(input[i]);
							if (isDuplicateCharExist) {
								// if dulicate char exist then skip the current look.
								continue;
							} else {
								boolean isRepeatedLetterExist = checkRepeatedLetterExist(
										result, input[i]);
								if (!isRepeatedLetterExist) {
									result = result + input[i];
								}
							}
						}
					}
					if(result.length()> 100){
						System.out.println("Maximum length of string should not exceeds than 100.");
					}else{
						jobId =new Random().nextInt(1000);
						resultMap.put(jobId, result.length());
					}
					
				}catch(Exception exception){
					System.out.println("error occured while processing the data.");
				}
				
			}else{
				System.out.println("minimun 1 and maximum 8 comma seprated string should be allowed.");
			}
		} else {
			System.out.println("Please enter the valid input String");
		}
		return "Job Id is : " + jobId
				+ " and length of the unique longest string is :"
				+ result.length();
	}

	private boolean checkRepeatedLetterExist(String concatString, String currentString) {
		boolean isRepeatedLetterExist = false;
		if(null!=concatString){
			if(!concatString.equalsIgnoreCase(currentString)){
				boolean isBreak = false;
				char[] concatInputCharArray = concatString.toCharArray();
				char[] currentStringCharArray = currentString.toCharArray();
				for (int i = 0; i < concatInputCharArray.length; i++) {
					for (int j = 0; j < currentStringCharArray.length; j++) {
						if(concatInputCharArray[i] == currentStringCharArray[j]){
							isRepeatedLetterExist = true;
							isBreak=true;
							break;
						}
					}
					if(isBreak){
						break;
					}
				}
			}else{
				isRepeatedLetterExist = true;
			}
		}
		return isRepeatedLetterExist;
	}

	private boolean checkDuplicateCharExist(String currentString) {
		boolean isDuplicateCharExist = false;
		boolean isBreak = false;
		char[] currentStringArray = currentString.toCharArray();
		for (int i = 0; i < currentStringArray.length; i++) {
			for (int j = i + 1; j < currentStringArray.length; j++) {
				if (currentStringArray[i] == currentStringArray[j]) {
					isDuplicateCharExist = true;
					isBreak = true;
					break;
				}
				if (isBreak) {
					break;
				}
			}
		}
		return isDuplicateCharExist;
	}
	
	
	public String getUniqueLengthByJobId(Integer jobID){
		String result = null;
		try{
			Integer count = resultMap.get(jobID);
			if(null!=count){
				result = "count of the unique length is "+count+ " store against the job id "+jobID;
			}else{
				result = "Please provide the valid job id.";
			}
		}catch(Exception exception){
			result = "Please provide the valid job id.";
		}
		return result;
	}

}
