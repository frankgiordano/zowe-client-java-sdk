/*
 * This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Copyright Contributors to the Zowe Project.
 */
package zosjobs;

import core.ZOSConnection;
import org.apache.http.client.methods.HttpPut;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import rest.IZoweRequest;
import rest.JsonRequest;
import rest.TextRequest;
import rest.ZosmfHeaders;
import utility.Util;
import zosjobs.input.SubmitJclParms;
import zosjobs.input.SubmitJobParms;
import zosjobs.response.Job;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SubmitJobs {

    private static final Logger LOG = LogManager.getLogger(SubmitJobs.class);

    public static Job submitJob(ZOSConnection connection, String jobDataSet) throws IOException {
        return SubmitJobs.submitJobCommon(connection, new SubmitJobParms(jobDataSet));
    }

    public static Job submitJobCommon(ZOSConnection connection, SubmitJobParms parms) throws IOException {
        Util.checkNullParameter(parms == null, "parms is null");
        Util.checkStateParameter(!parms.getJobDataSet().isPresent(), "jobDataSet not specified");

        String url = "https://" + connection.getHost() + ":" + connection.getPort() + JobsConstants.RESOURCE;
        LOG.debug(url);

        String fullyQualifiedDataset = "//'" + parms.getJobDataSet().get() + "'";
        JSONObject reqBody = new JSONObject();
        reqBody.put("file", fullyQualifiedDataset);
        LOG.debug(reqBody);

        if (parms.getJclSymbols().isPresent()) {
            // TODO..
        }

        IZoweRequest request = new JsonRequest(connection, new HttpPut(url), reqBody.toString());
        JSONObject result = request.httpPut();

        return Util.createJobObjFromJson(result);
    }

    /**
     * Submit a string of JCL to run
     * @static
     * @param {AbstractSession} session - z/OSMF connection info
     * @param {string} jcl - string of JCL that you want to be submit
     * @param {string} internalReaderRecfm - record format of the jcl you want to submit. "F" (fixed) or "V" (variable)
     * @param {string} internalReaderLrecl - logical record length of the jcl you want to submit
     * @returns {Job} - Job document with details about the submitted job
     * @memberof SubmitJobs
     */
    public static Job submitJcl(ZOSConnection connection, String jcl, String internalReaderRecfm,
                                String internalReaderLrecl) throws IOException {
        return SubmitJobs.submitJclCommon(connection, new SubmitJclParms(jcl, internalReaderRecfm, internalReaderLrecl));
    }

    private static Job submitJclCommon(ZOSConnection connection, SubmitJclParms parms) throws IOException {
        Util.checkConnection(connection);
        Util.checkNullParameter(parms == null, "parms is null");
        Util.checkStateParameter(!parms.getJcl().isPresent(), "jcl not specified");
        Util.checkStateParameter(parms.getJcl().get().isEmpty(), "jcl is empty");

        String key, value;
        Map<String, String> headers = new HashMap<>();

        key = ZosmfHeaders.HEADERS.get("X_IBM_INTRDR_MODE_TEXT").get(0);
        value = ZosmfHeaders.HEADERS.get("X_IBM_INTRDR_MODE_TEXT").get(1);
        headers.put(key, value);

        if (parms.getInternalReaderLrecl().isPresent()) {
            key = ZosmfHeaders.HEADERS.get("X_IBM_INTRDR_LRECL").get(0);
            headers.put(key, parms.getInternalReaderLrecl().get());
        } else {
            key = ZosmfHeaders.HEADERS.get("X_IBM_INTRDR_LRECL_80").get(0);
            value = ZosmfHeaders.HEADERS.get("X_IBM_INTRDR_LRECL_80").get(1);
            headers.put(key, value);
        }
        if (parms.getInternalReaderRecfm().isPresent()) {
            key = ZosmfHeaders.HEADERS.get("X_IBM_INTRDR_RECFM").get(0);
            headers.put(key, parms.getInternalReaderLrecl().get());
        } else {
            key = ZosmfHeaders.HEADERS.get("X_IBM_INTRDR_RECFM_F").get(0);
            value = ZosmfHeaders.HEADERS.get("X_IBM_INTRDR_RECFM_F").get(1);
            headers.put(key, value);
        }
        if (parms.getJclSymbols().isPresent()) {
            // TODO..
        }

        key = ZosmfHeaders.HEADERS.get("X_IBM_INTRDR_CLASS_A").get(0);
        value = ZosmfHeaders.HEADERS.get("X_IBM_INTRDR_CLASS_A").get(1);
        headers.put(key, value);

        String url = "https://" + connection.getHost() + ":" + connection.getPort() + JobsConstants.RESOURCE;
        LOG.debug(url);

        IZoweRequest request = new TextRequest(connection, new HttpPut(url), parms.getJcl().get());
        request.setHeaders(headers);

        String result = request.httpPut();
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(result);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Util.createJobObjFromJson(json);
    }

}
