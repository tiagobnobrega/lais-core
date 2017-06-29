package it.ninebee.lasa.laisfala.luis;

import lombok.Data;

@Data
public class LuisIntent {

   private String intent;
   private double score;
}
