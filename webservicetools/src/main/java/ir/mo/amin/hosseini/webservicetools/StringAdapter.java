//package ir.mo.amin.hosseini.commontools.WebServiceTools;
//
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonDeserializationContext;
//import com.google.gson.JsonDeserializer;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonParseException;
//
//import java.lang.reflect.Modifier;
//import java.lang.reflect.Type;
//
///**
// *
// */
//
//class StringAdapter implements JsonDeserializer {
//    @Override
//    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//        try {
//
//            String content = json.getAsString();
////            if (content.equals("0"))
////                if (typeOfT == String.class)
////                    return null;
//            if (content.equals("NODATA") || content.equals("[]") || content.equals(""))
//                return null;
//        } catch (IllegalStateException | UnsupportedOperationException e) {
//        }
//
//        return new GsonBuilder().
//                excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
////                .registerTypeAdapter(ParentProfile.class, new ParentProfileInstanceCreator())
//                .create().fromJson(json, typeOfT);
//    }
//}
