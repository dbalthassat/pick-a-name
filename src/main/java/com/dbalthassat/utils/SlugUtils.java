package com.dbalthassat.utils;

import java.text.Normalizer;
import java.util.Objects;

public class SlugUtils {
	private SlugUtils() {}

	public static String createSlug(String str) {
		Objects.requireNonNull(str);
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		return str.trim()
		          .toLowerCase()
				  .replaceAll("\\s+", " ")
				  .replaceAll(" ", "-")
				  .replaceAll("[^a-z0-9-]", "");
	}
}
