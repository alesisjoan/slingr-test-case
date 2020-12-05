from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from swagger_server.models.base_model_ import Model
from swagger_server import util


class Error(Model):

    def __init__(self, status_code: int = 200, error_list: str = None):  # noqa: E501

        self._status_code = status_code
        self._error_list = error_list

    def to_dict(self):
        return {'status_code': self._status_code, 'error_list': self._error_list}

    @classmethod
    def from_dict(cls, dikt) -> 'Error':
        return util.deserialize_model(dikt, cls)

    @property
    def status_code(self) -> int:
        return self._status_code

    @status_code.setter
    def status_code(self, status_code: int):

        self._status_code = status_code

    @property
    def error_list(self) -> str:

        return self._error_list

    @error_list.setter
    def error_list(self, error_list: str):
        """Sets the digits of this MathExpression.


        :param digits: The digits of this MathExpression.
        :type digits: int
        """

        self._error_list = error_list