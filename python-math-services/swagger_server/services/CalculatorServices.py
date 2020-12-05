import math
from ..models import Error
import logging

logging.getLogger('connexion.operation').setLevel('ERROR')

class Calculator(object):


    def calculate(self, expression, digits=0):
        if 'sqrt' in expression:
            expression = expression.replace('sqrt', 'math.sqrt')
        evaluated = None
        try:
            evaluated = eval(expression)
        except Exception as e:
            logging.error("Error while creating expression, please check the expression", e)
            error = Error(400, "Error while creating expression, please check the expression")
            return error.to_dict(), 400
        if digits > 0:
            s_digits = '%.' + str(int(digits)) + 'g'
            return '%s' % float(s_digits % evaluated)
        else:
            return evaluated
