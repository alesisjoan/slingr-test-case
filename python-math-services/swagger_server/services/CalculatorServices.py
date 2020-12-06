import math
from ..models import Error
import logging
import requests
from requests.utils import requote_uri
import os

logging.getLogger('connexion.operation').setLevel('ERROR')

cache_url_expressions = (os.environ.get("CACHE_HOST") or "http://localhost:8092") + "/expressions"


class Calculator(object):

    def calculate(self, expression, digits=0):
        if 'sqrt' in expression:
            expression = expression.replace('sqrt', 'math.sqrt')
        evaluated = None
        evaluated_cache = None
        try:
            evaluated_cache = self.get_cached_result(expression)
            ns = {'__builtins__': None}
            evaluated = evaluated_cache or eval(expression, ns)
        except Exception as e:
            logging.error("Error while creating expression, please check the expression", e)
            error = Error(400, "Error while creating expression, please check the expression")
            return error.to_dict(), 400
        if evaluated and not evaluated_cache:
            self.push_last_result(expression, evaluated)
        if digits > 0:
            s_digits = '%.' + str(int(digits)) + 'g'
            return '%s' % float(s_digits % evaluated)
        else:
            return evaluated

    def get_cached_result(self, expression):
        result = None

        try:
            result = requests.request("GET", cache_url_expressions + "?expression=" + requote_uri(expression))
        except Exception as e:
            logging.warning("Error pushing to cache services", e)
        return result.text

    def push_last_result(self, expression, result):

        payload = "{\"key\":  \"" + expression + "\", \"value\": \"" + str(result) + "\"}"
        headers = {
            'Content-Type': 'application/json'
        }

        try:
            requests.request("PUT", cache_url_expressions, headers=headers, data=payload)
        except Exception as e:
            logging.warning("Error pushing to cache services", e)
