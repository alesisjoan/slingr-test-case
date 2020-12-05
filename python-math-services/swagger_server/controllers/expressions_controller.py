import connexion
import six

from swagger_server.models.math_expression import MathExpression  # noqa: E501
from swagger_server import util

from ..services import CalculatorServices

calculator = CalculatorServices.Calculator()


def expressions_get(expression, digits=0):  # noqa: E501
    """calculate expression

     # noqa: E501

    :param expression: Expression to be evaluated
    :type expression: str
    :param digits: number of significant digits in formatted output. It is optional.
    :type digits: 

    :rtype: None
    """
    return calculator.calculate(expression, digits)


def expressions_post(body):  # noqa: E501
    """calculate expression

     # noqa: E501

    :param body: Expression Object
    :type body: dict | bytes

    :rtype: None
    """
    if connexion.request.is_json:
        body = MathExpression.from_dict(connexion.request.get_json())  # noqa: E501
    return calculator.calculate(body.expression, body.digits)
