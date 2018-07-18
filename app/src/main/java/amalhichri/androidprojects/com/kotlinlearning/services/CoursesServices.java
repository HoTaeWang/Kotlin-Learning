package amalhichri.androidprojects.com.kotlinlearning.services;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import amalhichri.androidprojects.com.kotlinlearning.utils.AppSingleton;

public class CoursesServices {


    private static CoursesServices this_ = new CoursesServices();

    public static synchronized CoursesServices getInstance()
    {
        if (this_==null) this_=new CoursesServices();
        return this_;
    }


    public void hasStartedCourse(String id, Context context, final ServerCallbacks serverCallbacks){
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, "http://192.168.1.5:80/ikotlinBackEnd/web/courses/getAllCourses?id="+id,  new JSONObject(), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (!response.has("Error")){
                            //
                            serverCallbacks.onSuccess(response);
                        }
                        else{
                            //
                            serverCallbacks.onWrong(response);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        serverCallbacks.onError(error);
                    }
                });
            jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                    5000,
                    3,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppSingleton.getInstance(context).addToRequestQueue(jsObjRequest, "getUserCourses");
    }



}