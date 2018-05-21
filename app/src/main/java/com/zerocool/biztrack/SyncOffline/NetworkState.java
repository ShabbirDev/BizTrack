package com.zerocool.biztrack.SyncOffline;//package com.crashoverride.keeptrack.SyncOffline;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.database.Cursor;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.crashoverride.keeptrack3.Retrieve.ListView.SurveyData;
//import com.crashoverride.keeptrack3.Retrieve.RetrieveDataRepo;
//import com.crashoverride.keeptrack3.Retrieve.UniversalDataSetter;
//import com.crashoverride.keeptrack3.SurveyDataHelper.DataManager.SurveyDataTable;
//import com.crashoverride.keeptrack3.SurveyDataHelper.app.App;
//
//import java.util.HashMap;
//import java.util.Map;
//
////import com.crashoverride.navdrawer.SurveyDataHelper.DataFragments.SubmitTest;
//
//
///**
// * Created by Technocrats on 19-Sep-17.
// */
//
//
//public class NetworkState extends BroadcastReceiver {
//
//    SurveyData surveyData = new SurveyData();
//    RequestQueue requestQueue;
//    private Context context;
//    private RetrieveDataRepo retrieveDataRepo = new RetrieveDataRepo();
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        this.context = context;
//        int i = 0;
//        int j;
//        while (i < 1) {
//            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//            //if there is a network
//            if (activeNetwork != null) {
//                //if connected to wifi or mobile data plan
//                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
//
//                    //i++;
//                    System.out.println("        ********************************CONNECTION ESTABLISHED****");
//                    //j++;
//                    Cursor cursor = retrieveDataRepo.getUnsyncedData();
//                    if (cursor.moveToFirst()) {
//                        do {
//                            UniversalDataSetter universalDataSetter = new UniversalDataSetter();
//
//                            universalDataSetter.setId(cursor.getInt(cursor.getColumnIndex(SurveyDataTable.COLOUMN_6)));
//                            universalDataSetter.setSyncstatus(cursor.getInt(cursor.getColumnIndex(SurveyDataTable.COLOUMN_8)));
//
//                            universalDataSetter.setProgramNameText(cursor.getString(cursor.getColumnIndex(SurveyDataTable.COLOUMN_5)));
//                            universalDataSetter.setDepartmentNameText(cursor.getString(cursor.getColumnIndex(SurveyDataTable.COLOUMN_6)));
//                            universalDataSetter.setFacultyText(cursor.getString(cursor.getColumnIndex(SurveyDataTable.COLOUMN_8)));
//                            universalDataSetter.setUniversityText(cursor.getString(cursor.getColumnIndex(SurveyDataTable.COLOUMN_8)));
//                            universalDataSetter.setGovernanceQuestion1(cursor.getString(cursor.getColumnIndex(SurveyDataTable.COLOUMN_5)));
//                            universalDataSetter.setGovernanceQuestion2(cursor.getString(cursor.getColumnIndex(SurveyDataTable.COLOUMN_21)));
//                            universalDataSetter.setCurriculumQuestion1(cursor.getString(cursor.getColumnIndex(SurveyDataTable.COLOUMN_23)));
//                            universalDataSetter.setCurriculumQuestion2(cursor.getString(cursor.getColumnIndex(SurveyDataTable.COLOUMN_14)));
//                            universalDataSetter.setTeachingLearningQuestion1(cursor.getString(cursor.getColumnIndex(SurveyDataTable.COLOUMN_26)));
//                            universalDataSetter.setTeachingLearningQuestion2(cursor.getString(cursor.getColumnIndex(SurveyDataTable.COLOUMN_26)));
//                            universalDataSetter.setAssessmentQuestion1(cursor.getString(cursor.getColumnIndex(SurveyDataTable.COLOUMN_28)));
//                            universalDataSetter.setAssessmentQuestion2(cursor.getString(cursor.getColumnIndex(SurveyDataTable.COLOUMN_28)));
//                            universalDataSetter.setInstitutionalStructuresAndFacilitiesQuestion1(cursor.getString(cursor.getColumnIndex(SurveyDataTable.COLOUMN_26)));
//                            universalDataSetter.setInstitutionalStructuresAndFacilitiesQuestion2(cursor.getString(cursor.getColumnIndex(SurveyDataTable.COLOUMN_27)));
//                            universalDataSetter.setStudentSupportQuestion1(cursor.getString(cursor.getColumnIndex(SurveyDataTable.COLOUMN_28)));
//                            universalDataSetter.setStudentSupportQuestion2(cursor.getString(cursor.getColumnIndex(SurveyDataTable.COLOUMN_29)));
//                            universalDataSetter.setResearchAndExtensionQuestion1(cursor.getString(cursor.getColumnIndex(SurveyDataTable.COLOUMN_19)));
//                            universalDataSetter.setResearchAndExtensionQuestion2(cursor.getString(cursor.getColumnIndex(SurveyDataTable.COLOUMN_20)));
//                            universalDataSetter.setStaffQuestion1(cursor.getString(cursor.getColumnIndex(SurveyDataTable.COLOUMN_21)));
//                            universalDataSetter.setStaffQuestion2(cursor.getString(cursor.getColumnIndex(SurveyDataTable.COLOUMN_22)));
//
//                            syncLocalData(universalDataSetter);
//
//
//                        } while (cursor.moveToNext());
//                    }
//
//
//                }
//            }
//            i++;
//        }
//    }
//
//
//    private void syncLocalData(final UniversalDataSetter universalDataSetter) {
//
//        requestQueue = Volley.newRequestQueue(App.getContext());
//
//        StringRequest request = new StringRequest(Request.Method.POST, SurveyDataTable.insertUrl, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                System.out.println("_________________________________ID:" + universalDataSetter.getId() + " : " + universalDataSetter.getSyncstatus());
//                universalDataSetter.setSyncstatus(SurveyData.SYNCED);
//                retrieveDataRepo.updateSyncStatus(universalDataSetter);
//                System.out.println("_________________________________ID:" + universalDataSetter.getId() + " : " + universalDataSetter.getSyncstatus());
//                //sending the broadcast to refresh the list
//                //context.sendBroadcast(new Intent(FinalSubmit.DATA_SAVED_BROADCAST));
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//
//            }
//        }) {
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> parameters = new HashMap<String, String>();
//
//                parameters.put("ProgramNameText", universalDataSetter.getProgramNameText());
//                parameters.put("DepartmentNameText", universalDataSetter.getDepartmentNameText());
//                parameters.put("FacultyText", universalDataSetter.getFacultyText());
//                parameters.put("UniversityText", universalDataSetter.getUniversityText());
//                parameters.put("GovernanceQuestion1", universalDataSetter.getGovernanceQuestion1());
//                parameters.put("GovernanceQuestion2", universalDataSetter.getGovernanceQuestion2());
//                parameters.put("CurriculumQuestion1", universalDataSetter.getCurriculumQuestion1());
//                parameters.put("CurriculumQuestion2", universalDataSetter.getCurriculumQuestion2());
//                parameters.put("TeachingLearningQuestion1", universalDataSetter.getTeachingLearningQuestion1());
//                parameters.put("TeachingLearningQuestion2", universalDataSetter.getTeachingLearningQuestion2());
//                parameters.put("AssessmentQuestion1", universalDataSetter.getAssessmentQuestion1());
//                parameters.put("AssessmentQuestion2", universalDataSetter.getAssessmentQuestion2());
//                parameters.put("InstitutionalStructuresAndFacilitiesQuestion1", universalDataSetter.getInstitutionalStructuresAndFacilitiesQuestion1());
//                parameters.put("InstitutionalStructuresAndFacilitiesQuestion2", universalDataSetter.getInstitutionalStructuresAndFacilitiesQuestion2());
//                parameters.put("StudentSupportQuestion1", universalDataSetter.getStudentSupportQuestion1());
//                parameters.put("StudentSupportQuestion2", universalDataSetter.getStudentSupportQuestion2());
//                parameters.put("ResearchAndExtensionQuestion1", universalDataSetter.getResearchAndExtensionQuestion1());
//                parameters.put("ResearchAndExtensionQuestion2", universalDataSetter.getResearchAndExtensionQuestion2());
//                parameters.put("StaffQuestion1", universalDataSetter.getStaffQuestion1());
//                parameters.put("StaffQuestion2", universalDataSetter.getStaffQuestion2());
//
//
//                System.out.println("PASSED........:  " + parameters.toString());
//
//                return parameters;
//            }
//        };
//        System.out.println("******************" + request.toString());
//        requestQueue.add(request);
//
//
//    }
//
//
//}
