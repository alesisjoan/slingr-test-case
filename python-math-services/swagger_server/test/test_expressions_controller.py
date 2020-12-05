# coding: utf-8

from __future__ import absolute_import

from flask import json
from six import BytesIO

from swagger_server.models.math_expression import MathExpression  # noqa: E501
from swagger_server.test import BaseTestCase


class TestExpressionsController(BaseTestCase):
    """ExpressionsController integration test stubs"""

    def test_expressions_get(self):
        """Test case for expressions_get

        calculate expression
        """
        query_string = [('expression', 'expression_example'),
                        ('digits', 8.14)]
        response = self.client.open(
            '//expressions',
            method='GET',
            content_type='application/json',
            query_string=query_string)
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_expressions_post(self):
        """Test case for expressions_post

        calculate expression
        """
        body = MathExpression()
        response = self.client.open(
            '//expressions',
            method='POST',
            data=json.dumps(body),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))


if __name__ == '__main__':
    import unittest
    unittest.main()
