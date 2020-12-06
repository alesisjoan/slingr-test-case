const API_SPRINGBOOT_ENDPOINT_GET =
  "http://localhost:8095/expressions?expression=";
const API_SPRINGBOOT_ENDPOINT_POST = "http://localhost:8090/expressions";

const API_PYTHON_ENDPOINT_GET = "http://localhost:8091/expressions?expression=";
const API_PYTHON_ENDPOINT_POST = "http://localhost:8091/expressions";

const PythonPOST = (expression, digits, callbackResult, callbackError) => {
  fetch(`${API_PYTHON_ENDPOINT_POST}`, {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      expression: expression,
      digits: parseInt(digits),
    }),
  })
    .then((response) => response.json())
    .then((responseJson) => {
      if (responseJson.status_code && responseJson.status_code === 400) {
        callbackError(responseJson.error_list);
      } else {
        callbackResult(responseJson);
      }
    })
    .catch((error) => {
      alert(error);
    });
};

const PythonGET = (expression, digits, callbackResult, callbackError) => {
  fetch(
    `${API_PYTHON_ENDPOINT_GET}` +
      encodeURIComponent(expression) +
      "&digits=" +
      digits,
    {
      headers: {
        "Cache-Control": "public",
      },
    }
  )
    .then((response) => response.json())
    .then((responseJson) => {
      if (responseJson.status_code && responseJson.status_code === 400) {
        callbackError(responseJson.error_list);
      } else {
        callbackResult(responseJson);
      }
    })
    .catch((error) => {
      alert(error);
    });
};

const SpringBootPOST = (expression, digits, callbackResult, callbackError) => {
  fetch(`${API_SPRINGBOOT_ENDPOINT_POST}`, {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      expression: expression,
      digits: digits,
    }),
  })
    .then((response) => response.json())
    .then((responseJson) => {
      if (responseJson.statusCode && responseJson.statusCode === 400) {
        callbackError(responseJson.errorList.map((x) => x.errorMessage));
      } else {
        callbackResult(responseJson);
      }
    })
    .catch((error) => {
      alert(error);
    });
};

const SpringBootGET = (expression, digits, callbackResult, callbackError) => {
  fetch(
    `${API_SPRINGBOOT_ENDPOINT_GET}` +
      encodeURIComponent(expression) +
      "&digits=" +
      digits,
    {
      headers: {
        "Cache-Control": "public",
      },
    }
  )
    .then((response) => response.json())
    .then((responseJson) => {
      if (responseJson.statusCode && responseJson.statusCode === 400) {
        callbackError(responseJson.errorList.map((x) => x.errorMessage));
      } else {
        callbackResult(responseJson);
      }
    })
    .catch((error) => {
      alert(error);
    });
};

export default function getCalculateAPI(api, method, expression) {
  let callback = null;
  if (api === "Python") {
    if (method === "POST") {
      callback = PythonPOST;
    } else {
      callback = PythonGET;
    }
  } else {
    if (method === "POST") {
      callback = SpringBootPOST;
    } else {
      callback = SpringBootGET;
    }
  }

  return callback;
}
