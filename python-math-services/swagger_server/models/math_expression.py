# coding: utf-8

from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from swagger_server.models.base_model_ import Model
from swagger_server import util


class MathExpression(Model):
    """NOTE: This class is auto generated by the swagger code generator program.

    Do not edit the class manually.
    """

    def __init__(self, expression: str=None, digits: int=0):  # noqa: E501
        """MathExpression - a model defined in Swagger

        :param expression: The expression of this MathExpression.  # noqa: E501
        :type expression: str
        :param digits: The digits of this MathExpression.  # noqa: E501
        :type digits: int
        """
        self.swagger_types = {
            'expression': str,
            'digits': int
        }

        self.attribute_map = {
            'expression': 'expression',
            'digits': 'digits'
        }

        self._expression = expression
        self._digits = digits

    @classmethod
    def from_dict(cls, dikt) -> 'MathExpression':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The MathExpression of this MathExpression.  # noqa: E501
        :rtype: MathExpression
        """
        return util.deserialize_model(dikt, cls)

    @property
    def expression(self) -> str:
        """Gets the expression of this MathExpression.


        :return: The expression of this MathExpression.
        :rtype: str
        """
        return self._expression

    @expression.setter
    def expression(self, expression: str):
        """Sets the expression of this MathExpression.


        :param expression: The expression of this MathExpression.
        :type expression: str
        """

        self._expression = expression

    @property
    def digits(self) -> int:
        """Gets the digits of this MathExpression.


        :return: The digits of this MathExpression.
        :rtype: int
        """
        return self._digits

    @digits.setter
    def digits(self, digits: int):
        """Sets the digits of this MathExpression.


        :param digits: The digits of this MathExpression.
        :type digits: int
        """

        self._digits = digits
