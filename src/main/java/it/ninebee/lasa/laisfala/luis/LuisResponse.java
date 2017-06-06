package it.ninebee.lasa.laisfala.luis;

import java.util.List;

import lombok.Data;

@Data
public class LuisResponse {
   private String query;
   private LuisIntent topScoringIntent;
   private List<LuisIntent> intents;
   private List<LuisEntity> entities;
   private List<LuisCompositeEntity> compositeEntities;
}
