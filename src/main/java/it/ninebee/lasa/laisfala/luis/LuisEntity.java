package it.ninebee.lasa.laisfala.luis;

import java.util.Map;

import lombok.Data;

@Data
public class LuisEntity {

   private String name;
   private String type;
   private int startIndex;
   private int endIndex;
   private double score;
   private Map<String, Object> resolution;
}
