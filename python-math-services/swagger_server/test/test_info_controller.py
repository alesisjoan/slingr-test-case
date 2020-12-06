# coding: utf-8

from __future__ import absolute_import

from flask import json
from six import BytesIO

from swagger_server.test import BaseTestCase


class TestInfoController(BaseTestCase):
    """InfoController integration test stubs"""

    def test_app_health_get(self):
        """Test case for app_health_get

        shows app info
        """
        response = self.client.open(
            '//app/health',
            method='GET')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_app_last_get(self):
        """Test case for app_last_get

        shows last (n) requested expressions
        """
        response = self.client.open(
            '//app/last',
            method='GET')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))



if __name__ == '__main__':
    import unittest
    unittest.main()
