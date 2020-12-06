import connexion
import six

from swagger_server import util

def app_health_get():  # noqa: E501
    """shows app info

     # noqa: E501


    :rtype: None
    """
    return 'System is working'

