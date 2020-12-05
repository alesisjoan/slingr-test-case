import connexion
import six

from swagger_server import util



def app_health_get():  # noqa: E501
    """shows app info

     # noqa: E501


    :rtype: None
    """
    return 'do some magic!'


def app_last_get():  # noqa: E501
    """shows last (n) requested expressions

     # noqa: E501


    :rtype: None
    """
    return 'do some magic!'


def app_top_get():  # noqa: E501
    """shows top most requested expressions

     # noqa: E501


    :rtype: None
    """
    return 'do some magic!'
